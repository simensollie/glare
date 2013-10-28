package resources;

import java.util.*;

import dal.*;

public class InstagramReaderDummy implements IReader{
	public List<PictureData> getPictures(String searchTag) {

		List<PictureData> pictureData = new ArrayList<PictureData>();
		
		PictureData p = new PictureData();
		p.setId("picID 1");
		p.setTime(1);
		p.setUrlStd("http://distilleryimage3.s3.amazonaws.com/ef1236282adb11e3a9b322000a9e5afc_6.jpg");
		p.setUrlThumb("http://distilleryimage3.s3.amazonaws.com/ef1236282adb11e3a9b322000a9e5afc_5.jpg");
		p.setHashtag("inst1");
		pictureData.add(p);
		
		p = new PictureData();
		p.setId("picID 2");
		p.setTime(2);
		p.setUrlStd("http://distilleryimage3.s3.amazonaws.com/ef1236282adb11e3a9b322000a9e5afc_8.jpg");
		p.setUrlThumb("http://images.ak.instagram.com/profiles/profile_186344368_75sq_1380238572.jpg");
		p.setHashtag("inst2");
		pictureData.add(p);
		
		p = new PictureData();
		p.setId("picID 4");
		p.setTime(4);
		p.setUrlStd("http://distilleryimage3.s3.amazonaws.com/ef1236282adb11e3a9b322000a9e5afc_8.jpg");
		p.setUrlThumb("http://images.ak.instagram.com/profiles/profile_186344368_75sq_1380238572.jpg");
		p.setHashtag("hashtag4");
		pictureData.add(p);

		p = new PictureData();
		p.setId("picID 2");
		p.setTime(2);
		p.setUrlStd("http://distilleryimage3.s3.amazonaws.com/ef1236282adb11e3a9b322000a9e5afc_8.jpg");
		p.setUrlThumb("http://images.ak.instagram.com/profiles/profile_186344368_75sq_1380238572.jpg");
		p.setHashtag("inst2SecTime");
		pictureData.add(p);
		
		return pictureData;
	}
}