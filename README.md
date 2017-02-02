# reimagined-fortnight 

![build](https://travis-ci.org/sbelkin/reimagined-fortnight.svg?branch=master)

Had an idea to use supervised machine learning to determine based on various features if i would like a particular drink. 

For now I would probably keep the beer,wine and hard liquor separate and then go through the process of seeing how it would work if they are part of a single model.

Decided to just get something running from being away. Used Elide and dropwizard to pull together a simple part of the the application with less overhead. 
Removing not needed items. 

More importantly the parts of the project:
    1. REST API - backend
    2. UI - frontend - todo
    3. Data / Machine Learning - learning -  todo 
 
This is the basic outline of the TODO's each part will probably be contained with in its own module. 

http://www.brewerydb.com/developers/docs-endpoint/beer_index


Pulling in google Vision Api: 
https://cloud.google.com/vision/reference/rest/v1/images/annotate#type -> do text detection on menus to extract beer lists.

POST https://vision.googleapis.com/v1/images:annotate?key={YOUR_API_KEY}
{
 "requests": [
  {
   "features": [
    {
     "type": "TEXT_DETECTION"
    }
   ],
   "image": {
    "source": {
     "gcsImageUri": "gs://bucket/menu.image"
    }
   }
  }
 ]
}

