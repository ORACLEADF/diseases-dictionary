package com.diseases.dictionary.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Environment;
import android.widget.ImageView;
import com.diseases.dictionary.R;

import java.io.File;

/**
 * Created by ameysami on 9/12/13.
 */
public final class Statics {
    private static Context context;
    private static Typeface fontTypeFace_Titr;
    private static Typeface fontTypeFace_Nazanin;
    private static Typeface fontTypeFace_Zar;
    private static Typeface fontTypeFace_ZarBold;
    private static Typeface fontTypeFace_Yekan;

    private static int colorOdd = Color.parseColor("#CCE3FF"); //#FFFDCD
    private static int colorEven = Color.parseColor("#FFDFD3"); //#BFFFD9
    private static int colorError = Color.RED;
    private static int colorHeader = Color.parseColor("#DBDBFF");
    private static int colorFooter = Color.parseColor("#DBDBFF");
    private static int colorHighLight = Color.parseColor("#CDFFC9");

    public static InsertCommas commas = new InsertCommas();

    public static void setContext(Context context) {
        Statics.context = context;
    }

    public static int getColorOdd() {
        return colorOdd;
    }

    public static int getColorHighLight() {
        return colorHighLight;
    }

    public static int getColorEven() {
        return colorEven;
    }

    public static int getColorError() {
        return colorError;
    }

    public static Typeface getFontTypeFace_Yekan() {
        if (fontTypeFace_Yekan == null)
            setFontTypeFace_Yekan();
        return fontTypeFace_Yekan;
    }

    public static Typeface getFontTypeFace_Titr() {
        if (fontTypeFace_Titr == null)
            setFontTypeFace_Titr();
        return fontTypeFace_Titr;
    }

    public static Typeface getFontTypeFace_Nazanin() {
        if (fontTypeFace_Nazanin == null)
            setFontTypeFace_Nazanin();
        return fontTypeFace_Nazanin;
    }

    public static Typeface getFontTypeFace_Zar() {
        if (fontTypeFace_Zar == null)
            setFontTypeFace_Zar();
        return fontTypeFace_Zar;
    }

    public static Typeface getFontTypeFace_ZarBold() {
        if (fontTypeFace_ZarBold == null)
            setFontTypeFace_ZarBold();
        return fontTypeFace_ZarBold;
    }

    private static void setFontTypeFace_Yekan() {
        Statics.fontTypeFace_Yekan = Typeface.createFromAsset(context.getAssets(), "fonts/Yekan.ttf");
    }

    private static void setFontTypeFace_Titr() {
        Statics.fontTypeFace_Titr = Typeface.createFromAsset(context.getAssets(), "fonts/Titr.ttf");
    }

    private static void setFontTypeFace_Nazanin() {
        Statics.fontTypeFace_Nazanin = Typeface.createFromAsset(context.getAssets(), "fonts/Nazanin.ttf");
    }

    private static void setFontTypeFace_Zar() {
        Statics.fontTypeFace_Zar = Typeface.createFromAsset(context.getAssets(), "fonts/Zar.ttf");
    }

    private static void setFontTypeFace_ZarBold() {
        Statics.fontTypeFace_ZarBold = Typeface.createFromAsset(context.getAssets(), "fonts/ZarBold.ttf");
    }

    private static String pathApp = Environment.getExternalStorageDirectory() + "/App/";

    public static void setImg1(ImageView imgView, String imgFolderName, String imgId) {
        //release 1 which is written by me
        String imgPathPng = pathApp + imgFolderName + "/" + imgId;
        File imgFilePng = new File(imgPathPng);
        if (imgFilePng.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgPathPng);
            imgView.setImageBitmap(myBitmap);
        } else {
            imgView.setImageResource(R.drawable.image_not_found);
        }
    }

    public static void setImg2(ImageView imgView, String imgFolderName, String imgId) {
        String imgPathPng = pathApp + imgFolderName + "/" + imgId;
        File imgFilePng = new File(imgPathPng);

        int mDstWidth;
        int mDstHeight;
        if (imgFolderName.equals("Goods")) {
            mDstWidth = context.getResources().getDimensionPixelSize(R.dimen.destination_width_goods);
            mDstHeight = context.getResources().getDimensionPixelSize(R.dimen.destination_height_goods);
        } else if (imgFolderName.equals("GoodsGroup")) {
            mDstWidth = context.getResources().getDimensionPixelSize(R.dimen.destination_width_goodsgroup);
            mDstHeight = context.getResources().getDimensionPixelSize(R.dimen.destination_height_goodsgroup);
        } else {
            mDstWidth = context.getResources().getDimensionPixelSize(R.dimen.destination_width);
            mDstHeight = context.getResources().getDimensionPixelSize(R.dimen.destination_height);
        }

        Bitmap unscaledBitmap = null;
        Bitmap scaledBitmap = null;

        if (imgFilePng.exists())
            // Part 1: Decode image
            unscaledBitmap = ScalingUtilities.decodeResource2(imgPathPng, mDstWidth, mDstHeight, ScalingUtilities.ScalingLogic.FIT);
        else {
            // Part 1: Decode image
            int mSourceId = R.drawable.image_not_found;
            unscaledBitmap = ScalingUtilities.decodeResource(context.getResources(), mSourceId, mDstWidth, mDstHeight, ScalingUtilities.ScalingLogic.FIT);
        }

        // Part 2: Scale image
        scaledBitmap = Bitmap.createScaledBitmap(unscaledBitmap, mDstWidth, mDstHeight, true);
        unscaledBitmap.recycle();

        imgView.setImageBitmap(scaledBitmap);
    }

    public static void setImg3(ImageView imgView, String imgFolderName, String imgId, int mDstWidth, int mDstHeight) {
        String imgPathPng = pathApp + imgFolderName + "/" + imgId;
        File imgFilePng = new File(imgPathPng);

        Bitmap unscaledBitmap = null;
        Bitmap scaledBitmap = null;

        if (imgFilePng.exists())
            // Part 1: Decode image
            unscaledBitmap = ScalingUtilities.decodeResource2(imgPathPng, mDstWidth, mDstHeight, ScalingUtilities.ScalingLogic.CROP);
        else {
            // Part 1: Decode image
            int mSourceId = R.drawable.image_not_found;
            unscaledBitmap = ScalingUtilities.decodeResource(context.getResources(), mSourceId, mDstWidth, mDstHeight, ScalingUtilities.ScalingLogic.CROP);
        }

        // Part 2: Scale image
        scaledBitmap = Bitmap.createScaledBitmap(unscaledBitmap, mDstWidth, mDstHeight, true);
        unscaledBitmap.recycle();

        imgView.setImageBitmap(scaledBitmap);
    }

    public Statics(Context ctx) {
        this.setContext(ctx);
    }
}
