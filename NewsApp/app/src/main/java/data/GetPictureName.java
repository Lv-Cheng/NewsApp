package data;

/**
 * Created by admin on 2016/5/26.
 */
public class GetPictureName {

    public static String getPictureName(String url){

        String[] splitsString = url.split("/");

        if(splitsString.length > 0){

            String[] fileNames = splitsString[splitsString.length - 1].split("\\.");

            return fileNames[0];
        }

        return null;
    }
}
