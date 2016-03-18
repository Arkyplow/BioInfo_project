package utils;

import semiGlobal.fragment.Fragment;

import java.io.*;
import java.util.ArrayList;

/**
 * gestion fichiers
 * @author Jannou Brohee
 */
public class FileHelper {

    public static ArrayList<Fragment> load (String path){
        ArrayList<Fragment> retour = new ArrayList<Fragment>();
        try{
            File f = new File (path);
            FileReader fr = new FileReader (f);
            BufferedReader br = new BufferedReader (fr);
            try
            {
                String line = br.readLine();
                String buff ="";
                while (line != null){
                    if(!line.isEmpty()){
                        if(line.contains(">") && !buff.isEmpty()){
                            retour.add(new Fragment(buff));
                            buff = "";
                        }
                        else if(!line.contains(">")){
                            buff += line;
                        }
                    }
                    line = br.readLine();
                }
                retour.add(new Fragment(buff));
                br.close();
                fr.close();
            }
            catch (IOException exception)
            {
                System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
            }
        }catch (FileNotFoundException exception){
            System.out.println ("Le fichier n'a pas été trouvé");
        }
        return retour;
    }
}
