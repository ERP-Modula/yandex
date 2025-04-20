package modula.platform.yandex.tracker.controller;

import lombok.RequiredArgsConstructor;
import modula.platform.yandex.tracker.domain.Issue;
import modula.platform.yandex.tracker.domain.IssueCreateRequest;
import modula.platform.yandex.tracker.domain.IssueFindRequest;
import modula.platform.yandex.tracker.domain.IssuePatchRequest;
import modula.platform.yandex.tracker.service.IssueServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/yandex-tracker/api/issues/")
@RequiredArgsConstructor
public class IssueController {
    private final IssueServiceImpl issueService;

    @PostMapping("/{userId}")
    public ResponseEntity<Issue> createIssue(
            @PathVariable UUID userId,
            @RequestBody IssueCreateRequest request
    ){

        Issue createdIssue = issueService.createIssue(userId, request);
        return ResponseEntity.ok(createdIssue);
    }

    @PatchMapping("/{userId}/{issueId}")
    public ResponseEntity<Issue> updateIssue(
            @PathVariable UUID userId,
            @PathVariable String issueId,
            @RequestBody IssuePatchRequest request
    ){

        Issue createdIssue = issueService.updateIssue(userId, issueId, request);
        return ResponseEntity.ok(createdIssue);
    }

    @PostMapping("/{userId}/{issueId}/{queueId}")
    public ResponseEntity<Issue> moveIssue(
            @PathVariable UUID userId,
            @PathVariable String issueId,
            @PathVariable String queueId,
            @RequestBody IssuePatchRequest request
    ){
        Issue createdIssue = issueService.moveIssue(userId, issueId, queueId, request);
        return ResponseEntity.ok(createdIssue);
    }

    @PostMapping("/find/{userId}")
    public ResponseEntity<List<Issue>> findIssue(
            @PathVariable UUID userId,
            @RequestBody IssueFindRequest request
    ){
        List<Issue> createdIssue = issueService.findIssue(userId, request);
        return ResponseEntity.ok(createdIssue);
    }
}
