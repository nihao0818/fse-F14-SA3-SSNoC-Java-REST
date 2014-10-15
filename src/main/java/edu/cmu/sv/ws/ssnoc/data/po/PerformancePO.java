package edu.cmu.sv.ws.ssnoc.data.po;

import com.google.gson.Gson;

/**
 * Created by Vignan on 10/14/2014.
 */
public class PerformancePO {

        private long postsPerSecond;
        private long getPerSecond;

        public void setPostsPerSecond(long postsPerSecond){this.postsPerSecond=postsPerSecond;}

        public long getPostsPerSecond(){return postsPerSecond;}

        public void setGetPerSecond(long getPerSecond){this.getPerSecond=getPerSecond;}

        public long getGetPerSecond(){return getPerSecond;}

        @Override
        public String toString() {
            return new Gson().toJson(this);
        }
}
