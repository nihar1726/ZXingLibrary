package com.example.mypractice.video_player

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.net.Uri               // <-- ADD THIS
import android.widget.MediaController // <-- ADD THIS
import com.example.mypractice.video_player.databinding.FragmentFirstBinding
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    // --- ONLY ONE DECLARATION BLOCK IS NEEDED ---
    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    // ---------------------------------------------


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Get the VideoView using the fragment's binding
        val videoView = binding.videoView // Ensure XML ID is @+id/video_view or @+id/videoView

        // 2. Set up playback
        val mediaController = MediaController(requireContext()) // <-- Use requireContext()
        mediaController.setAnchorView(videoView)

        // Ensure R.raw.katseye_in_seoul_ep_01 exists
        val uri: Uri = Uri.parse("android.resource://" + requireContext().packageName
                + "/" + R.raw.katseye_in_seoul_ep_01)

        videoView.setMediaController(mediaController)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()

        // Example navigation logic:
//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        // Release the binding to prevent memory leaks
        _binding = null
    }
}