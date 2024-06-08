package mdk.mutils.github;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Repository {
    @SerializedName("id")
    private long id;
    @SerializedName("node_id")
    private String nodeId;
    @SerializedName("name")
    private String name;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("private")
    private boolean isPrivate;
    @SerializedName("owner")
    private User owner;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("description")
    private String description;
    @SerializedName("fork")
    private boolean fork;
    @SerializedName("url")
    private String url;
    @SerializedName("forks_url")
    private String forksUrl;
    @SerializedName("keys_url")
    private String keysUrl;
    @SerializedName("collaborators_url")
    private String collaboratorsUrl;
    @SerializedName("teams_url")
    private String teamsUrl;
    @SerializedName("hooks_url")
    private String hooksUrl;
    @SerializedName("issue_events_url")
    private String issueEventsUrl;
    @SerializedName("events_url")
    private String eventsUrl;
    @SerializedName("assignees_url")
    private String assigneesUrl;
    @SerializedName("branches_url")
    private String branchesUrl;
    @SerializedName("tags_url")
    private String tagsUrl;
    @SerializedName("blobs_url")
    private String blobsUrl;
    @SerializedName("git_tags_url")
    private String gitTagsUrl;
    @SerializedName("git_refs_url")
    private String gitRefsUrl;
    @SerializedName("trees_url")
    private String treesUrl;
    @SerializedName("statuses_url")
    private String statusesUrl;
    @SerializedName("languages_url")
    private String languagesUrl;
    @SerializedName("stargazers_url")
    private String stargazersUrl;
    @SerializedName("contributors_url")
    private String contributorsUrl;
    @SerializedName("subscribers_url")
    private String subscribersUrl;
    @SerializedName("subscription_url")
    private String subscriptionUrl;
    @SerializedName("commits_url")
    private String commitsUrl;
    @SerializedName("git_commits_url")
    private String gitCommitsUrl;
    @SerializedName("comments_url")
    private String commentsUrl;
    @SerializedName("issue_comment_url")
    private String issueCommentUrl;
    @SerializedName("contents_url")
    private String contentsUrl;
    @SerializedName("compare_url")
    private String compareUrl;
    @SerializedName("merges_url")
    private String mergesUrl;
    @SerializedName("archive_url")
    private String archiveUrl;
    @SerializedName("downloads_url")
    private String downloadsUrl;
    @SerializedName("issues_url")
    private String issuesUrl;
    @SerializedName("pulls_url")
    private String pullsUrl;
    @SerializedName("milestones_url")
    private String milestonesUrl;
    @SerializedName("notifications_url")
    private String notificationsUrl;
    @SerializedName("labels_url")
    private String labelsUrl;
    @SerializedName("releases_url")
    private String releasesUrl;
    @SerializedName("deployments_url")
    private String deploymentsUrl;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("pushed_at")
    private String pushedAt;
    @SerializedName("git_url")
    private String gitUrl;
    @SerializedName("ssh_url")
    private String sshUrl;
    @SerializedName("clone_url")
    private String cloneUrl;
    @SerializedName("svn_url")
    private String svnUrl;
    @SerializedName("homepage")
    private String homepage;
    @SerializedName("size")
    private int size;
    @SerializedName("stargazers_count")
    private int stargazersCount;
    @SerializedName("watchers_count")
    private int watchersCount;
    @SerializedName("language")
    private String language;
    @SerializedName("has_issues")
    private boolean hasIssues;
    @SerializedName("has_projects")
    private boolean hasProjects;
    @SerializedName("has_downloads")
    private boolean hasDownloads;
    @SerializedName("has_wiki")
    private boolean hasWiki;
    @SerializedName("has_pages")
    private boolean hasPages;
    @SerializedName("has_discussions")
    private boolean hasDiscussions;
    @SerializedName("forks_count")
    private int forksCount;
    @SerializedName("mirror_url")
    private String mirrorUrl;
    @SerializedName("archived")
    private boolean archived;
    @SerializedName("disabled")
    private boolean disabled;
    @SerializedName("open_issues_count")
    private int openIssuesCount;
    @SerializedName("license")
    private String license;
    @SerializedName("allow_forking")
    private boolean allowForking;
    @SerializedName("is_template")
    private boolean isTemplate;
    @SerializedName("web_commit_signoff_required")
    private boolean webCommitSignoffRequired;
    @SerializedName("topics")
    private List<String> topics;
    @SerializedName("visibility")
    private String visibility;
    @SerializedName("forks")
    private int forks;
    @SerializedName("open_issues")
    private int openIssues;
    @SerializedName("watchers")
    private int watchers;
    @SerializedName("default_branch")
    private String defaultBranch;
    @SerializedName("temp_clone_token")
    private String tempCloneToken;
    @SerializedName("network_count")
    private int networkCount;
    @SerializedName("subscribers_count")
    private int subscribersCount;
}
