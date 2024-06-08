package mdk.mutils.github;

import lombok.Getter;
import com.google.gson.annotations.SerializedName;
import java.util.List;

@Getter
public class Release {
    @SerializedName("url")
    private String url;
    @SerializedName("assets_url")
    private String assetsUrl;
    @SerializedName("upload_url")
    private String uploadUrl;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("id")
    private long id;
    @SerializedName("author")
    private User author;
    @SerializedName("node_id")
    private String nodeId;
    @SerializedName("tag_name")
    private String tagName;
    @SerializedName("target_commitish")
    private String targetCommitish;
    @SerializedName("name")
    private String name;
    @SerializedName("draft")
    private boolean draft;
    @SerializedName("prerelease")
    private boolean prerelease;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("published_at")
    private String publishedAt;
    @SerializedName("assets")
    private List<Asset> assets;
    @SerializedName("tarball_url")
    private String tarballUrl;
    @SerializedName("zipball_url")
    private String zipballUrl;
    @SerializedName("body")
    private String body;

    @Getter
    public static class Asset {
        @SerializedName("url")
        private String url;
        @SerializedName("id")
        private long id;
        @SerializedName("node_id")
        private String nodeId;
        @SerializedName("name")
        private String name;
        @SerializedName("label")
        private String label;
        @SerializedName("uploader")
        private User uploader;
        @SerializedName("content_type")
        private String contentType;
        @SerializedName("state")
        private String state;
        @SerializedName("size")
        private int size;
        @SerializedName("download_count")
        private int downloadCount;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("updated_at")
        private String updatedAt;
        @SerializedName("browser_download_url")
        private String browserDownloadUrl;
    }
}