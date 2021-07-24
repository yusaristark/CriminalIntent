package com.bignerdranch.android.criminalintent

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import java.io.File

private const val ARG_FILEPATH = "filepath"

class FullPictureViewFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val layoutInflater = requireActivity().layoutInflater
        val view = layoutInflater.inflate(R.layout.fragment_full_picture_view, null)

        val builder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
        builder.setView(view)

        val imageView: ImageView = view.findViewById(R.id.full_image)

        val filePath = arguments?.getString(ARG_FILEPATH)
        if (filePath != null) {
            val bitmap = PictureUtils().getScaledBitmap(filePath, requireActivity())
            imageView.setImageBitmap(bitmap)
        } else {
            imageView.setImageBitmap(null)
        }
        return builder.create()
    }
    
    companion object {
        fun newInstance(photoFile: File): FullPictureViewFragment {
            val args = Bundle().apply {
                putString(ARG_FILEPATH, photoFile.path)
            }
            
            return FullPictureViewFragment().apply {
                arguments = args
            }
        }
    }
}