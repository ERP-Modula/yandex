package modula.platform.yandex.tracker.service;

import lombok.RequiredArgsConstructor;
import modula.platform.yandex.tracker.client.YandexTrackerIssueClient;
import modula.platform.yandex.tracker.domain.*;
import modula.platform.yandex.tracker.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService{

    private final ClientRepository clientRepository;
    private final YandexTrackerIssueClient yandexTrackerIssueClient;

    public Issue createIssue(UUID userId, IssueCreateRequest request){
        Client client = clientRepository.findByModulaId(userId)
                .orElseThrow(() -> new RuntimeException("Organization id not found for user id: " + userId));

        return yandexTrackerIssueClient.createIssue(client.getOrgId(), request);
    }

    public Issue updateIssue(UUID userId, String issueId, IssuePatchRequest request){
        Client client = clientRepository.findByModulaId(userId)
                .orElseThrow(() -> new RuntimeException("Organization id not found for user id: " + userId));

        return yandexTrackerIssueClient.updateIssue(client.getOrgId(), issueId, request);
    }

    public Issue moveIssue(UUID userId, String issueId, String queueId, IssuePatchRequest request){
        Client client = clientRepository.findByModulaId(userId)
                .orElseThrow(() -> new RuntimeException("Organization id not found for user id: " + userId));

        return yandexTrackerIssueClient.moveIssue(client.getOrgId(), issueId, queueId, request);
    }

    public List<Issue> findIssue(UUID userId, IssueFindRequest request){
        Client client = clientRepository.findByModulaId(userId)
                .orElseThrow(() -> new RuntimeException("Organization id not found for user id: " + userId));

        return yandexTrackerIssueClient.findIssue(client.getOrgId(), request);
    }
}
