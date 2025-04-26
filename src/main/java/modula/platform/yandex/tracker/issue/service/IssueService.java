package modula.platform.yandex.tracker.issue.service;

import modula.platform.yandex.tracker.issue.domain.Issue;
import modula.platform.yandex.tracker.issue.dto.IssueCreateRequest;
import modula.platform.yandex.tracker.issue.dto.IssueFindRequest;
import modula.platform.yandex.tracker.issue.dto.IssuePatchRequest;

import java.util.List;
import java.util.UUID;

public interface IssueService {
    Issue createIssue(String orgID, IssueCreateRequest request);
    Issue updateIssue(String orgID, String issueId, IssuePatchRequest request);
    Issue moveIssue(String orgID, String issueId, String queueId, IssuePatchRequest request);
    List<Issue> findIssue(String orgID, IssueFindRequest request);
}
