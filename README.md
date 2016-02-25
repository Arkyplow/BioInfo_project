# BioInfo_project
Projet de bio-informatique 

#Note : 
(loc) : commande pour branche local 

(net) : commande pour branche Github

la branche par dft est origin/Dev et non origin/master. 

origin/Dev et origin/master seront mergés à des moments clés du projet.

https://openclassrooms.com/courses/gerez-vos-codes-source-avec-git


#CMD
-mettre à jour les fichiers depuis le dépot : git pull (loc) (net)

-afficher les fichiers modifiés depuis dernier commit : git status (loc)(net)

-ajouter fichier F au snap pour commit : git add F (loc)(net)

-commit un snap avec description : git commit -m "description" (loc)(net)

-afficher les info d'un commit : git log -p (loc)(net)

-ajouter fichier commité au depot : git push (net)

-annuler un commit erz654er après qu'il ai été push sur une branche distante (il faut push après avoir revert ) : git revert erz654er  (net)

-sauvegarder travail avant de changer de branche ( au lieu de devoir commit) : git stash (loc)(net)

-créer une branche local B : ~$ git branch B (loc)

-pour aller sur une branche B = ~$ git checkout B (loc) (net)

-fusionner une branche B avec la branche courante : git merge B (loc)



#How to work :
/!\ Ne jamais merger sur origin/Master....

Pour dev un feature :

->Créer une branch MyDev (git branch MyDev) en local. 

->Une fois qu'on a finis sur MyDev et que tout fonctionne (teste etc etc ) aller sur origin/Dev puis 

    1-->merge ( attention toujours faire un git pull avant !)
    
    2-->commit -m
    
    3-->push
    
->La new feature sera sur le depot à ce moment là.

 

#Rappels :

TOUJOURS FAIRE  "git pull" AVANT DE FAIRE QUOI QUE CE SOIT SUR LE PROJET car je gère trèèèèèèèèèèèèèèès mal les conflits...

Avant de push il faut toujours faire un git commit -m "description" cela permettra garder un dépot propre et de faciliter les chose s'il y a un probleme (hope) . 
