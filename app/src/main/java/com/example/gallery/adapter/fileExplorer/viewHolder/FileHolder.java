package com.example.gallery.adapter.fileExplorer.viewHolder;

import android.content.Context;
import android.graphics.PorterDuff;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import com.example.gallery.R;
import com.example.gallery.themes.Theme;
import com.example.gallery.data.models.File_POJO;
import com.example.gallery.data.Settings;
import com.example.gallery.data.models.StorageRoot;
import com.example.gallery.util.MediaType;

public class FileHolder extends RecyclerView.ViewHolder {

    public FileHolder(View itemView) {
        super(itemView);
    }

    public void setFile(File_POJO file) {
        setSelected(false);
        ImageView folderIndicator = itemView.findViewById(R.id.folder_indicator);
        if (file instanceof StorageRoot) {
            if (file.getName().equals(itemView.getContext().getString(R.string.storage))) {
                folderIndicator.setImageResource(R.drawable.ic_smartphone_white);
            } else {
                folderIndicator.setImageResource(R.drawable.ic_sd_card_white);
            }
        } else if (!file.isMedia) {
            if (new File(file.getPath()).isFile()) {
                folderIndicator.setImageResource(R.drawable.ic_insert_drive_file_white);
            } else {
                folderIndicator.setImageResource(R.drawable.ic_folder_white);
            }
        } else if (MediaType.isVideo(file.getPath())) {
            folderIndicator.setImageResource(R.drawable.ic_videocam_white);
        } else {
            folderIndicator.setImageResource(R.drawable.ic_photo_white);
        }
        TextView textView = itemView.findViewById(R.id.text);
        textView.setText(file.getName());
    }

    public void setSelected(boolean selected) {
        Context context = itemView.getContext();

        Settings s = Settings.getInstance(itemView.getContext());
        Theme theme = s.getThemeInstance(context);

        int color = selected ?
                theme.getAccentColor(context)
                : ContextCompat.getColor(context, android.R.color.transparent);
        itemView.setBackgroundColor(color);

        TextView textView = itemView.findViewById(R.id.text);
        textView.setTextColor(selected ? theme.getAccentTextColor(context)
                : theme.getTextColorPrimary(context));

        ImageView folderIndicator = itemView.findViewById(R.id.folder_indicator);
        folderIndicator.setColorFilter(selected ? theme.getAccentTextColor(context)
                : theme.getTextColorSecondary(context), PorterDuff.Mode.SRC_IN);
    }
}
