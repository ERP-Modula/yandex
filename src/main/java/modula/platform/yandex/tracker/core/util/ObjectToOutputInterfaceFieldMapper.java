package modula.platform.yandex.tracker.core.util;

import com.modula.common.domain.moduleconfiguration.InterfaceFieldType;
import com.modula.common.domain.moduleconfiguration.OutputInterface;
import com.modula.common.domain.workflow.execution.OutputInterfaceField;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.stream.Collectors;

public class ObjectToOutputInterfaceFieldMapper {

    public static List<OutputInterfaceField> mapObjectToFields(List<OutputInterface> interfaceTemplate, Object sourceObject, boolean isFirstCall)
            throws IllegalAccessException, InvocationTargetException {

        List<OutputInterfaceField> result = new ArrayList<>();
        if (interfaceTemplate == null || sourceObject == null) {
            return result;
        }

        Class<?> sourceClass = sourceObject.getClass();
        Map<String, Method> getterMethods = Arrays.stream(sourceClass.getMethods())
                .filter(m -> m.getName().startsWith("get") || m.getName().startsWith("is"))
                .collect(Collectors.toMap(
                        m -> m.getName().replaceFirst("^(get|is)", "").toLowerCase(),
                        m -> m
                ));

        if (isFirstCall) {
            OutputInterfaceField classInfoField = new OutputInterfaceField();
            classInfoField.setName(interfaceTemplate.get(0).getName());
            classInfoField.setParamTypeEnum(InterfaceFieldType.ARRAY);
            classInfoField.setValue(sourceObject.getClass().getName());
            classInfoField.setSpec(new ArrayList<>());
            result.add(classInfoField);
            interfaceTemplate = interfaceTemplate.get(0).getSpec();
        }
//        OutputInterfaceField rootOutputField = new OutputInterfaceField();
//        rootOutputField.setName(sourceClass.getName());
//        rootOutputField.setValue("object");
//        rootOutputField.setParamTypeEnum(InterfaceFieldType.ARRAY);

        for (OutputInterface interfaceField : interfaceTemplate) {
            OutputInterfaceField outputField = new OutputInterfaceField();
//            outputField.setId(UUID.randomUUID());
            outputField.setName(interfaceField.getName());
            outputField.setParamTypeEnum(interfaceField.getParamTypeEnum());

            // Получаем значение из исходного объекта
            String fieldName = interfaceField.getName();
            Method getter = getterMethods.get(fieldName.toLowerCase());
            Object fieldValue = null;

            if (getter != null) {
                try {
                    fieldValue = getter.invoke(sourceObject);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    // Логируем ошибку или обрабатываем по необходимости
                }
            }

            outputField.setValue(convertValueToString(fieldValue, interfaceField.getParamTypeEnum()));

            // Обрабатываем вложенные объекты
            if (!interfaceField.getSpec().isEmpty() && fieldValue != null) {
                if (isCollectionOrArray(fieldValue.getClass())) {
                    outputField.setSpec(processCollection(interfaceField.getSpec(), fieldValue));
                } else {
                    outputField.setSpec(mapObjectToFields(interfaceField.getSpec(), fieldValue, false));
                }
            } else {
                outputField.setSpec(new ArrayList<>());
            }

            if (isFirstCall)
                result.get(0).getSpec().add(outputField);
            else
                result.add(outputField);
        }

        return result;
    }

    private static List<OutputInterfaceField> processCollection(List<OutputInterface> interfaceTemplate, Object collection)
            throws IllegalAccessException, InvocationTargetException {

        List<OutputInterfaceField> result = new ArrayList<>();

        if (collection instanceof Object[]) {
            for (Object item : (Object[]) collection) {
                result.addAll(mapObjectToFields(interfaceTemplate, item, false));
            }
        } else if (collection instanceof Collection) {
            for (Object item : (Collection<?>) collection) {
                result.addAll(mapObjectToFields(interfaceTemplate, item, false));
            }
        }

        return result;
    }

    private static String convertValueToString(Object value, InterfaceFieldType fieldType) {
        if (value == null) {
            return null;
        }

        switch (fieldType) {
            case BUFFER:
                if (value instanceof byte[]) {
                    return Base64.getEncoder().encodeToString((byte[]) value);
                } else if (value instanceof ByteBuffer) {
                    ByteBuffer buffer = (ByteBuffer) value;
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    return Base64.getEncoder().encodeToString(bytes);
                }
                return value.toString();
            case DATE:
                // Можно добавить форматирование даты
                return value.toString();
            case NUMBER:
                return value.toString();
            case BOOLEAN:
                return Boolean.toString((Boolean) value);
            case ARRAY:
                return "[...]"; // Или другое представление для массивов
            default:
                return value.toString();
        }
    }

    private static boolean isCollectionOrArray(Class<?> type) {
        return type.isArray() || Collection.class.isAssignableFrom(type);
    }
}