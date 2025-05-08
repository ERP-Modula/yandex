//package modula.platform.yandex.tracker.issue.controller;
//
//import lombok.RequiredArgsConstructor;
//import modula.platform.yandex.tracker.issue.domain.Issue;
//import modula.platform.yandex.tracker.issue.dto.IssueCreateRequest;
//import modula.platform.yandex.tracker.issue.dto.IssueFindRequest;
//import modula.platform.yandex.tracker.issue.dto.IssuePatchRequest;
//import modula.platform.yandex.tracker.issue.service.IssueServiceImpl;
//import org.apache.kafka.common.protocol.types.Field;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/yandex-tracker/api/issues/")
//@RequiredArgsConstructor
//public class IssueController {
//    private final IssueServiceImpl issueService;
//
//    @PostMapping("/{userId}")
//    public ResponseEntity<Issue> createIssue(
//            @PathVariable String orgID,
//            @RequestBody IssueCreateRequest request
//    ){
//
//        Issue createdIssue = issueService.createIssue(orgID, request);
//        return ResponseEntity.ok(createdIssue);
//    }
//
//    @PatchMapping("/{userId}/{issueId}")
//    public ResponseEntity<Issue> updateIssue(
//            @PathVariable String orgID,
//            @PathVariable String issueId,
//            @RequestBody IssuePatchRequest request
//    ){
//
//        Issue createdIssue = issueService.updateIssue(orgID, issueId, request);
//        return ResponseEntity.ok(createdIssue);
//    }
//
//    @PostMapping("/{userId}/{issueId}/{queueId}")
//    public ResponseEntity<Issue> moveIssue(
//            @PathVariable String orgID,
//            @PathVariable String issueId,
//            @PathVariable String queueId,
//            @RequestBody IssuePatchRequest request
//    ){
//        Issue createdIssue = issueService.moveIssue(orgID, issueId, queueId, request);
//        return ResponseEntity.ok(createdIssue);
//    }
//
//    @PostMapping("/find/{userId}")
//    public ResponseEntity<List<Issue>> findIssue(
//            @PathVariable String orgID,
//            @RequestBody IssueFindRequest request
//    ){
//        List<Issue> createdIssue = issueService.findIssue(orgID, request);
//        return ResponseEntity.ok(createdIssue);
//    }
//}
