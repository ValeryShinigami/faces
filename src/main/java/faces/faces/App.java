package faces.faces;


import java.io.IOException;
import java.net.MalformedURLException;

import java.util.List;


import org.openimaj.image.FImage;

import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.RGBColour;
import org.openimaj.image.colour.Transforms;
import org.openimaj.image.processing.face.detection.DetectedFace;
import org.openimaj.image.processing.face.detection.FaceDetector;
import org.openimaj.image.processing.face.detection.HaarCascadeDetector;
import org.openimaj.image.processing.face.detection.keypoints.FKEFaceDetector;
import org.openimaj.image.processing.face.detection.keypoints.KEDetectedFace;
import org.openimaj.video.VideoDisplay;
import org.openimaj.video.VideoDisplayListener;
import org.openimaj.video.capture.VideoCapture;


public class App 
{
	public static void main( String[] args ) throws MalformedURLException, IOException 
	{

		VideoCapture vc = new VideoCapture( 320, 240 );
		VideoDisplay<MBFImage> vd = VideoDisplay.createVideoDisplay( vc );
		vd.addVideoListener( 
				new VideoDisplayListener<MBFImage>() {
					public void beforeUpdate( MBFImage frame ) {


						FaceDetector<DetectedFace,FImage> fd = new HaarCascadeDetector(80);
						List<DetectedFace> faces = fd.detectFaces( Transforms.calculateIntensity(frame));
						
						//détection des points clés du visage (nez, bouche , yeux etc..)
						/*FaceDetector<KEDetectedFace,FImage> fd = new FKEFaceDetector();
						List<KEDetectedFace> faces = fd.detectFaces( Transforms.calculateIntensity( frame ) );*/

						for( DetectedFace face : faces ) {
							frame.drawShape(face.getShape(), RGBColour.GREEN);

						}

					}

					public void afterUpdate( VideoDisplay<MBFImage> display ) {
					}
				});
	}


}
