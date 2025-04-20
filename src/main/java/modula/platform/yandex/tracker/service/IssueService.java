package modula.platform.yandex.tracker.service;

import modula.platform.yandex.tracker.domain.Issue;
import modula.platform.yandex.tracker.domain.IssueCreateRequest;
import modula.platform.yandex.tracker.domain.IssueFindRequest;
import modula.platform.yandex.tracker.domain.IssuePatchRequest;

import java.util.List;
import java.util.UUID;

public interface IssueService {
    Issue createIssue(UUID userId, IssueCreateRequest request);
    Issue updateIssue(UUID userId, String issueId, IssuePatchRequest request);
    Issue moveIssue(UUID userId, String issueId, String queueId, IssuePatchRequest request);
    List<Issue> findIssue(UUID userId, IssueFindRequest request);
}
