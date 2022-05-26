package up.visulog.gitrawdata;

public class CommitBuilder {
    /** commit attributes */
    private final String id;
    private String author;
    private String date;
    private String description;
    private String mergedFrom;

    public CommitBuilder(String id) {
        /** set id author to the commit */
        this.id = id;
    }

    public CommitBuilder setAuthor(String author) {
        /** definition of author name */
        this.author = author;
        return this;
    }

    public CommitBuilder setDate(String date) {
        /** setting the commit time */
        this.date = date;
        return this;
    }

    public CommitBuilder setDescription(String description) {
        /** setting the commit description */
        this.description = description;
        return this;
    }

    public CommitBuilder setMergedFrom(String mergedFrom) {
        /** definition of merg source */
        this.mergedFrom = mergedFrom;
        return this;
    }

    public Commit createCommit() {
        /** construction of the commit object */
        return new Commit(id, author, date, description, mergedFrom);
    }
}