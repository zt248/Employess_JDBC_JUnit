package entity;

public class Department {

    private Long id;
    private String title;

    public Department() {

    }

    public Department(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
