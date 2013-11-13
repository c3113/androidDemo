package com.hilary.test;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Selection;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/** hilary Create on @2013-11-13. */

/**
 * 
 * hilary create on 2013-11-13.
 *
 * 
 */
public class EditTextImageView extends EditText {

	public Html.ImageGetter mImageGetter;

	private int mSelStart, mSelEnd;

	public EditTextImageView(Context context) {
		super(context);
		init();
	}

	public EditTextImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public EditTextImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public void setImageGetter(Html.ImageGetter imageGetter) {
		mImageGetter = imageGetter;

	}

	public void init() {
		this.addTextChangedListener(new DWTextWatcher());
		mImageGetter = new ImageGetter() {

			@Override
			public Drawable getDrawable(String source) {
				Drawable drawable = Drawable.createFromPath(source);
				if (drawable == null) {
					return null;
				}
				drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
						drawable.getIntrinsicHeight());
				return drawable;
			}
		};
	}

	@Override
	protected void onSelectionChanged(int selStart, int selEnd) {
		super.onSelectionChanged(selStart, selEnd);
		mSelStart = selStart;
		mSelEnd = selEnd;
	}

	/**
	 * selection Convenience for replace
	 * 
	 * @param text
	 */
	public void replaceChar(CharSequence text) {
		replaceChar(text, mSelStart, mSelEnd);
	}

	public void replaceChar(CharSequence text, int start, int end) {
		Editable table = getEditableText();
		table.replace(start, end, text);
	}

	/**
	 * 插入图片
	 * 
	 * @param imgPath
	 *            加上img标签的src
	 */
	public void insertImage(String imgPath) {
		Spanned e = Html.fromHtml("<img src=\"" + imgPath +"\" />", mImageGetter, null);
		replaceChar(e);
	}

	/**
	 * 移除图片
	 * 
	 * @param path
	 */
	public void removeInmage(String path) {
		setText(Html.fromHtml(
				Html.toHtml(getText())
						.replace("<img src=\"" + path + "\">", ""),
				mImageGetter, null));
	}

	/**
	 * 以HTML方法添加到控件中
	 * 
	 * @param text
	 */
	public void setHtmlText(String text) {
		setText(Html.fromHtml(text, mImageGetter, null));
	}
	
	public class DWTextWatcher implements TextWatcher {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
		}

		@Override
		public void afterTextChanged(Editable s) {
			int position = Selection.getSelectionStart(EditTextImageView.this
					.getText());
			mSelStart = mSelEnd = position;
		}
		
	}
}
