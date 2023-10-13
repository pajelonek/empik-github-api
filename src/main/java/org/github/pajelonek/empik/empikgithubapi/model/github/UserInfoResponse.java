package org.github.pajelonek.empik.empikgithubapi.model.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
    private String login;
    private int id;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("avatar_url")

    private String avatarUrl;
    @JsonProperty("gravatar_id")

    private String gravatarId;

    private String url;
    @JsonProperty("html_url")

    private String htmlUrl;
    @JsonProperty("followers_url")

    private String followersUrl;
    @JsonProperty("following_url")

    private String followingUrl;
    @JsonProperty("gists_url")

    private String gistsUrl;
    @JsonProperty("starred_url")

    private String starredUrl;
    @JsonProperty("subscriptions_url")

    private String subscriptionsUrl;
    @JsonProperty("organizations_url")

    private String organizationsUrl;
    @JsonProperty("repos_url")

    private String reposUrl;
    @JsonProperty("events_url")

    private String eventsUrl;
    @JsonProperty("received_events_url")

    private String receivedEventsUrl;

    private String type;
    @JsonProperty("site_admin")

    private boolean siteAdmin;
    private String name;
    private String company;
    private String blog;
    private String location;
    private String email;
    private boolean hireable;
    private String bio;
    @JsonProperty("twitter_username")

    private String twitterUsername;
    @JsonProperty("public_repos")

    private int publicRepos;
    @JsonProperty("public_gists")

    private int publicGists;
    private int followers;
    private int following;
    @JsonProperty("created_at")

    private Date createdAt;
    @JsonProperty("updated_at")

    private Date updatedAt;
}
