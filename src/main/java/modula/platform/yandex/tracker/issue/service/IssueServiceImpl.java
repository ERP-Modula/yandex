package modula.platform.yandex.tracker.issue.service;

import lombok.RequiredArgsConstructor;
import modula.platform.yandex.tracker.issue.client.YandexTrackerIssueClient;
import modula.platform.yandex.tracker.issue.domain.Issue;
import modula.platform.yandex.tracker.issue.dto.IssueCreateRequest;
import modula.platform.yandex.tracker.issue.dto.IssueFindRequest;
import modula.platform.yandex.tracker.issue.dto.IssuePatchRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final YandexTrackerIssueClient yandexTrackerIssueClient;

    public Issue createIssue(String orgId, IssueCreateRequest request){

        return yandexTrackerIssueClient.createIssue(orgId, request);
    }

    public Issue updateIssue(String orgId, String issueId, IssuePatchRequest request){
        return yandexTrackerIssueClient.updateIssue(orgId, issueId, request);
    }

    public Issue moveIssue(String orgId, String issueId, String queueId, IssuePatchRequest request){
        return yandexTrackerIssueClient.moveIssue(orgId, issueId, queueId, request);
    }

    public List<Issue> findIssue(String orgId, IssueFindRequest request){
        return yandexTrackerIssueClient.findIssue(orgId, request);
    }
}
