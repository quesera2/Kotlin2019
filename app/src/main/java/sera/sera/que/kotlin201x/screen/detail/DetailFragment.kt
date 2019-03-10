package sera.sera.que.kotlin201x.screen.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.android.viewmodel.ext.android.viewModel
import sera.sera.que.kotlin201x.databinding.DetailFragmentBinding
import sera.sera.que.kotlin201x.screen.detail.DetailFragmentArgs.fromBundle

class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModel()

    private lateinit var binding: DetailFragmentBinding

    private val page by lazy { fromBundle(requireNotNull(arguments)).page }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webView.loadUrl("https://ja.wikipedia.org/w/index.php?curid=${page.pageid}")
    }
}
