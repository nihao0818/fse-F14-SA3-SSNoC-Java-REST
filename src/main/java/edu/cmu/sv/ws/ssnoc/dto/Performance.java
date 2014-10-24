package edu.cmu.sv.ws.ssnoc.dto;


import com.google.gson.Gson;

/**
 * Created by Vignan on 10/14/2014.
 */
public class Performance {

    private double postsPerSecond;
    private double getPerSecond;

    public void setPostsPerSecond(double postsPerSecond){this.postsPerSecond=postsPerSecond;}

    public double getPostsPerSecond(){return postsPerSecond;}

    public void setGetPerSecond(double getPerSecond){this.getPerSecond=getPerSecond;}

    public double getGetPerSecond(){return getPerSecond;}

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
