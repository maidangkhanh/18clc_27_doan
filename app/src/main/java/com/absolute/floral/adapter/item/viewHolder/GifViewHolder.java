package com.absolute.floral.adapter.item.viewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;
import com.absolute.floral.R;
import com.absolute.floral.data.models.AlbumItem;
import com.absolute.floral.ui.ItemActivity;
import com.absolute.floral.util.ItemViewUtil;

public class GifViewHolder extends ViewHolder {

    private PhotoViewAttacher attacher;

    public GifViewHolder(AlbumItem albumItem, int position) {
        super(albumItem, position);
    }

    @Override
    public View inflateView(ViewGroup container) {
        ViewGroup v = super.inflatePhotoView(container);
        v.removeView(v.findViewById(R.id.subsampling));
        View view = v.findViewById(R.id.image);

        ItemViewUtil.bindTransitionView((ImageView) view, albumItem);
        return v;
    }

    private void reloadGif() {
        View view = itemView.findViewById(R.id.image);
        ItemViewUtil.bindGif(this, (ImageView) view, albumItem);
    }

    public void setAttacher(ImageView imageView) {
        attacher = new PhotoViewAttacher(imageView);
        attacher.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {
                imageOnClick(view);
            }
        });
    }

    @Override
    public void onSharedElementEnter() {
        reloadGif();
    }

    @Override
    public void onSharedElementExit(final ItemActivity.Callback callback) {
        if (attacher != null) {
            attacher.cleanup();
            attacher = null;
        }
        callback.done();
    }

    @Override
    public void onDestroy() {
        if (attacher != null) {
            attacher.cleanup();
            attacher = null;
        }
        super.onDestroy();
    }
}
