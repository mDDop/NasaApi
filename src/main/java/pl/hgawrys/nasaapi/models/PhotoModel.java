package pl.hgawrys.nasaapi.models;

public class PhotoModel {
    private String title;
    private String description;
    private String url;

    public PhotoModel(String title, String description, String url) {
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public PhotoModel(Builder builder) {
        title = builder.getTitle();
        description = builder.getDescription();
        url = builder.getUrl();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class Builder{
        private String title;
        private String description;
        private String url;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description){
            this.description = description;
            return this;
        }

        public Builder setUrl(String url){
            this.url = url;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getUrl() {
            return url;
        }

        public PhotoModel build(){
            return new PhotoModel(this);
        }
    }
}
