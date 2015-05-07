/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.logic;


import twitter4j.Twitter;
import twitter4j.*;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Yves Maris
 */
public class TwitterAuthentication {
   final String CONSUMER_KEY="fpoSlUCWaxdidOnpK89ofpYJF";
   final String CONSUMER_SECRET="AAxgma4C5OoZiWgT0m76PH5Ct68gnrAKKp3jW7CxatCqVjhq1F";
   private Twitter twitter;
   private RequestToken requestToken;

    public Twitter getTwitter() {
        return twitter;
    }

    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }

    public RequestToken getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(RequestToken requestToken) {
        this.requestToken = requestToken;
    }
    
    public String getauthenticationURL(/*String requestURL*/) throws TwitterException{
            ConfigurationBuilder cb = new ConfigurationBuilder();

       // cb.setApplicationOnlyAuthEnabled(true);

        //cb.setOAuth2TokenType(token.getTokenType());
        //cb.setOAuth2AccessToken(token.getAccessToken());
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET);
        Twitter twitter = new TwitterFactory(cb.build()).getInstance();
        this.twitter=twitter;
        //request.setAttribute("twitter", twitter);
            RequestToken requestToken = twitter.getOAuthRequestToken(/*requestURL*/);
            this.requestToken = requestToken;
            //request.getSession().setAttribute("requestToken", requestToken);
            return requestToken.getAuthenticationURL();
    }
            
}
