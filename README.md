glare
=====

DAT210 Programvareutvikling

Glare - The ultimate Instagram and Twitter stalking searcher!
=============================================================

Glare is a tool to search for picture on Twitter and Instagram. The application runs in fullscreen showing the pictures you searched for, just like a picture frame. There is posibilities to manage hashtags, pictures, displaytime through a settings panel. 

How to run Glare
================
 
Database

     	1. Add connection named "glare" in MySQLWorkbench
     	2. Set username as "root"
     	3. No password
     	4. Create schema named "glare"
     	5. You're good to go, take a sip of Diet Coke:)
     	
Access tokens

      1. Create conf.ini and save it in the src/resource folder.
      2. Add following information:
             consumer_key="your twitter consumer key"
             consumer_secret="your twitter consumer secret"
             access_token="your instagram access token"
             access_token_secret="your instagram access token secret"
             client_id="your instagram client id"

   Run the application and begin stalking.
   
If you want to run Hibernate Tests:

     1. Click on "hibernate.cfg.xml" in src folder
     2. Change from "update" to "create" where it says "hbm2ddl.auto"
     3. Run hibernate/db tests
     4. Change back from "create" to "update" when you want to run the application
