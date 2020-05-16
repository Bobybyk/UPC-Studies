
if [ ! $(basename $(pwd)) = IS1 ]; then
    echo
    echo Bah alors !! Tu n\'as pas lu l\'énoncé ?!
    echo Tu dois m\'éxecuter depuis IS1 !!!
    echo
    exit 1
fi
   


mkdir -p TP3/Canards/CoffreDePicsou

cd TP3

echo 'bip bip!' > bip_bip
echo "Quoi d'neuf, docteur?" > bugs_bunny
echo "Tu es méprisable" > daffy_duck
echo "Faprifti" > grosminet
echo "Z'ai cru voir passer un rominet" > titi



cd Canards
touch daisy rapetou gus_glouton
echo "Oh, chouette chouette chouette!" > donald 
echo "Mon sou fétiche! Où est passé mon sou fétiche?" > picsou 
echo "C'est Donald qui l'a perdu, onc'Picsou..." > FiFi
ln FiFi RiRi


cd CoffreDePicsou
touch rubis lingots pepites emeraudes perles pieces_d_or
mkdir .sou_fetiche


mkdir ../../Toutou
cd ../../Toutou


echo 'echo Bonjour $USER !' > tata


echo 'vous ne pourrez pas me lire!' > titi


cp tata toto


chmod 400 tata
chmod 200 titi
chmod 500 toto


cp /bin/date tutu
chmod 100 tutu
