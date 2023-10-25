//Class for Post
//Shaylin Velen
//10 April 2023

public class Post {

/* A class representing a post

@param: title: the title of the post
@param: vidName: video name of file
@param: likes: number of likes

*/ 

    private String title, vidName;
    private int likes;
     
    public Post(String title, String vidName, int likes) {
        
        this.title = title;
        this.vidName = vidName;
        this.likes = likes;


    }
    
    public String getTitle() {
    
    /* returns the title of the post
    
    */
        
        return title;
    }
    
    public String getVidName() {
    
    /**
     returns the video name
    
    */
    return vidName; 
    }
    
    public int getLikes() {
    /**
     returns the number of likes
    */
    
    return likes;
    }
    
    public String toString() {
    
    /**
     returns the formatted post with title, video name and number of likes
    */
    
    
     return "Title: " + title + "\n" + "Video: "  + vidName + "\n" + "Number of likes: " + Integer.toString(likes) + "\n";
    
    }
    
}
