package id.anggra.blogpostbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "blogposts")
public class Blogpost
{
    @Id
    private String id;
    private String title;
    private String content;

    public Blogpost()
    {
    }

    public Blogpost(String title, String content)
    {
        this.title = title;
        this.content = content;
    }

    public String getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    @Override
    public String toString()
    {
        return "Blogpost [id=" + id + ", title=" + title + ", content=" + content + "]";
    }
}
