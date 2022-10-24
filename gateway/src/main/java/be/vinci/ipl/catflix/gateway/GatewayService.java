package be.vinci.ipl.catflix.gateway;

import be.vinci.ipl.catflix.gateway.data.AuthenticationProxy;
import be.vinci.ipl.catflix.gateway.data.ReviewsProxy;
import be.vinci.ipl.catflix.gateway.data.UsersProxy;
import be.vinci.ipl.catflix.gateway.data.VideosProxy;
import be.vinci.ipl.catflix.gateway.models.*;
import org.springframework.stereotype.Service;

@Service
public class GatewayService {

    private final AuthenticationProxy authenticationProxy;
    private final ReviewsProxy reviewsProxy;
    private final UsersProxy usersProxy;
    private final VideosProxy videosProxy;

    public GatewayService(AuthenticationProxy authenticationProxy,
                          ReviewsProxy reviewsProxy,
                          UsersProxy usersProxy,
                          VideosProxy videosProxy) {
        this.authenticationProxy = authenticationProxy;
        this.reviewsProxy = reviewsProxy;
        this.usersProxy = usersProxy;
        this.videosProxy = videosProxy;
    }

    public String connect(Credentials credentials) {
        return authenticationProxy.connect(credentials);
    }

    public String verify(String token) {
        return authenticationProxy.verify(token);
    }

    public void createUser(UserWithCredentials user) {
        usersProxy.createUser(user.getPseudo(), user.toUser());
        authenticationProxy.createCredentials(user.getPseudo(), user.toCredentials());
    }

    public User readUser(String pseudo) {
        return usersProxy.readUser(pseudo);
    }

    public void updateUser(UserWithCredentials user) {
        usersProxy.updateUser(user.getPseudo(), user.toUser());
        authenticationProxy.updateCredentials(user.getPseudo(), user.toCredentials());
    }

    public void deleteUser(String pseudo) {
        reviewsProxy.deleteReviewsFromUser(pseudo);
        videosProxy.deleteVideosFromAuthor(pseudo);
        authenticationProxy.deleteCredentials(pseudo);
        usersProxy.deleteUser(pseudo);
    }

    public Iterable<Video> readVideos() {
        return videosProxy.readVideos();
    }

    public void createVideo(Video video) {
        usersProxy.readUser(video.getAuthor());
        videosProxy.createVideo(video.getHash(), video);
    }

    public Video readVideo(String hash) {
        return videosProxy.readVideo(hash);
    }

    public void updateVideo(Video video) {
        videosProxy.updateVideo(video.getHash(), video);
    }

    public void deleteVideo(String hash) {
        reviewsProxy.deleteReviewsOfVideo(hash);
        videosProxy.deleteVideo(hash);
    }

    public Iterable<Video> readVideosFromUser(String pseudo) {
        usersProxy.readUser(pseudo);
        return videosProxy.readVideosFromAuthor(pseudo);
    }

    public Review createReview(NoIdReview review) {
        usersProxy.readUser(review.getPseudo());
        videosProxy.readVideo(review.getHash());
        return reviewsProxy.createReview(review);
    }

    public Review readReview(long id) {
        return reviewsProxy.readReview(id);
    }

    public void updateReview(Review review) {
        reviewsProxy.updateReview(review.getId(), review);
    }

    public void deleteReview(long id) {
        reviewsProxy.deleteReview(id);
    }

    public Iterable<Review> readReviewsFromUser(String pseudo) {
        usersProxy.readUser(pseudo);
        return reviewsProxy.readReviewsFromUser(pseudo);
    }

    public Iterable<Review> readReviewsOfVideo(String hash) {
        videosProxy.readVideo(hash);
        return reviewsProxy.readReviewsOfVideo(hash);
    }

    public Iterable<Video> readBestVideos() {
        return reviewsProxy.readBestVideos();
    }

}