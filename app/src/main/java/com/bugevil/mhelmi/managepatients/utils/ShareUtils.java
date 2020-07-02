package com.bugevil.mhelmi.managepatients.utils;

import android.content.Context;
import android.content.Intent;
import com.bugevil.mhelmi.managepatients.R;

public class ShareUtils {

  public static void shareText(Context context, String content) {
    /*Create an ACTION_SEND Intent*/
    Intent intent = new Intent(android.content.Intent.ACTION_SEND);
    /*The type of the content is text, obviously.*/
    intent.setType("text/plain");
    /*Applying information Subject and Body.*/
    intent.putExtra(android.content.Intent.EXTRA_SUBJECT,
      context.getString(R.string.share_subject));
    intent.putExtra(android.content.Intent.EXTRA_TEXT, content);
    /*Fire!*/
    context.startActivity(Intent.createChooser(intent,
      context.getString(R.string.share_your_results_with_your_friends)));
  }
}
