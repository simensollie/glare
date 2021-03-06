package unit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dal.ConfigurationReader;
import dal.PictureData;
import dal.TwitterReader;

public class TwitterReaderTest {

		private List<PictureData> pictures;
		private TwitterReader tr;
		
		@Before
		public void setUp() throws Exception {
			pictures = new ArrayList<PictureData>();
			tr = new TwitterReader(new ConfigurationReader());
			
			pictures = tr.getPictures("winter");
		}
		@After
		public void tearDown() {
			pictures = null;
		}
		
		@Test
		public void ReturnsListWithPictures(){
			assertThat(pictures.size(), is(not(nullValue())));
		}
		@Test
		public void FirstPictureHasUrlStandardRes(){
			assertThat(pictures.get(0).getUrlStd(), is(not(nullValue())));
		}
		@Test
		public void FirstPictureHasUrlThumbnailRes(){
			assertThat(pictures.get(0).getUrlThumb(), is(not(nullValue())));
		}
		@Test
		public void FirstPictureHasTime(){
			assertThat(pictures.get(0).getCreatedTime(), is(not(nullValue())));
		}
		@Test
		public void FirstPictureHasID(){
			assertThat(pictures.get(0).getId(), is(not(nullValue())));
		}
		@Test
		public void FirstPictureHasRemoveFlag(){
			assertThat(pictures.get(0).isRemoveFlag(), is(false));
		}
}
