package hackers.purdue.firstbusinesscompany.fridged;

import android.app.DownloadManager;
import android.media.Image;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by bscholer on 10/15/16.
 */
public class Recipe {

    private String publisher;
    private String title;
    private Image image;
    private URL url;
    private double socialRank;

    public Recipe(String publisher, String title, Image image, URL url, double socialRank) {
        this.publisher = publisher;
        this.title = title;
        this.image = image;
        this.url = url;
        this.socialRank = socialRank;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public double getSocialRank() {
        return socialRank;
    }

    public void setSocialRank(double socialRank) {
        this.socialRank = socialRank;
    }

    public static ArrayList<Recipe> getRecipes(ArrayList<String> ingredients)  {
        try {
            URL requestURL = new URL("http://food2fork.com/api/search");
            HttpURLConnection client = (HttpURLConnection) requestURL.openConnection();
            client.setRequestMethod("POST");
            client.setRequestProperty();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
