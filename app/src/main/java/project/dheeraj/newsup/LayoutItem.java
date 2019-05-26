package project.dheeraj.newsup;

public class LayoutItem {

    private String newsImage;
    private String newsTitle;
    private String newsDescription;
    private String newsContent;
    private String imgId;

    public LayoutItem(String imageResource, String titleResource, String descriptionResource, String contentResource, String imgIdRes){
        newsImage = imageResource;
        newsTitle = titleResource;
        newsDescription = descriptionResource;
        newsContent = contentResource;
        imgId = imgIdRes;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public String getImgId() {
        return imgId;
    }
}
