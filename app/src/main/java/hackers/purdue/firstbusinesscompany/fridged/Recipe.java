package hackers.purdue.firstbusinesscompany.fridged;

import android.graphics.Bitmap;
import android.media.Image;

import java.net.URL;

/**
 * Created by bscholer on 10/15/16.
 */
public class Recipe {


    private String publisher;
    private String title;
    private Bitmap image;
    private URL url;
    private double socialRank;

    public Recipe() {
    }


    public Recipe(String publisher, String title, Bitmap image, URL url, double socialRank) {
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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
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
}
