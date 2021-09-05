// Sourced: https://newbedev.com/android-recyclerview-gridlayoutmanager-column-spacing
package moe.swap.aniapp.ui.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(private val spanCount: Int,
                                private val spacing: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount
        outRect.apply {
            left = spacing - column * spacing / spanCount
            right = (column + 1) * spacing / spanCount
            bottom = spacing;
        }
    }
}