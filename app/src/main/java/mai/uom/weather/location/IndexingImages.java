package mai.uom.weather.location;

/**
 * Created by Panos on 16/1/2016.
 */
public class IndexingImages {
    private String [] ids;
    private int [] resIds;

    private void IndexingImages() {
        ids = new String[18];
        resIds = new int[18];
        initStringIds();
        initResourcesIds();
    }
    public int getImageResources(String id) {
        int index = -1;
        for(int i = 0 ; i < 18; i++) {
            if(ids[i].equals(id)) {
                index = i;
                break;
            }
        }
        if(index!= -1) {
            /** Retur
            return resIds[index];
        }
        /** Return the default image **/
        return R.drawable.w_01d;
    }
    /**
     * Indexing the ids
     */
    private void initStringIds() {
        ids[0] = "01d";
        ids[1] = "01n";
        ids[2] = "02d";
        ids[3] = "02n";
        ids[4] = "03d";
        ids[5] = "03n";
        ids[6] = "04d";
        ids[7] = "04n";
        ids[8] = "09d";
        ids[9] = "09n";
        ids[10] = "10d";
        ids[11] = "10n";
        ids[12] = "11d";
        ids[13] = "11n";
        ids[14] = "13d";
        ids[15] = "13n";
        ids[16] = "50d";
        ids[17] = "50n";
    }

    /**
     * Indexing the images resources
     */
    private void initResourcesIds() {
        resIds[0] = R.drawable.w_01d;
        resIds[1] = R.drawable.w_01n;
        resIds[2] = R.drawable.w_02d;
        resIds[3] = R.drawable.w_02n;
        resIds[4] = R.drawable.w_03d;
        resIds[5] = R.drawable.w_03n;
        resIds[6] = R.drawable.w_04d;
        resIds[7] = R.drawable.w_04n;
        resIds[8] = R.drawable.w_09d;
        resIds[9] = R.drawable.w_09n;
        resIds[10] = R.drawable.w_10d;
        resIds[11] = R.drawable.w_10n;
        resIds[12] = R.drawable.w_11d;
        resIds[13] = R.drawable.w_11n;
        resIds[14] = R.drawable.w_13d;
        resIds[15] = R.drawable.w_13n;
        resIds[16] = R.drawable.w_50d;
        resIds[17] = R.drawable.w_50n;
    }





}
