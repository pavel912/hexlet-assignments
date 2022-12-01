package exercise;

public class Article {
    private Long id;
    private String title;
    private String body;

    public Article(Long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Article(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
