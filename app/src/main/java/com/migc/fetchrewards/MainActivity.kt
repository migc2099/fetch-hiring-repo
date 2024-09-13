package com.migc.fetchrewards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.migc.fetchrewards.ui.theme.FetchRewardsTheme
import com.migc.fetchrewards.ui.theme.Typography
import com.migc.fetchrewards.ui.theme.backgroundColor
import com.migc.fetchrewards.ui.theme.mainColor
import com.migc.fetchrewards.util.Dimen.LARGE_PADDING
import com.migc.fetchrewards.util.Dimen.MEDIUM_PADDING
import com.migc.fetchrewards.util.Dimen.SMALL_ELEVATION
import com.migc.fetchrewards.util.Dimen.SMALL_PADDING
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = koinViewModel()
            val items = viewModel.items.collectAsState()

            FetchRewardsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn(modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor)) {
                        items.value.forEach { group ->
                            stickyHeader {
                                Surface(
                                    modifier = Modifier.fillMaxWidth(),
                                    color = mainColor,
                                    shadowElevation = SMALL_ELEVATION
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.list_id_text),
                                            fontSize = Typography.titleLarge.fontSize
                                        )
                                        Spacer(modifier = Modifier.width(SMALL_PADDING))
                                        Text(
                                            text = group.key.toString(),
                                            fontSize = Typography.titleLarge.fontSize,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }

                            }
                            items(group.value) { item ->
                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Text(
                                        modifier = Modifier.weight(1f),
                                        text = item.id.toString(),
                                        textAlign = TextAlign.End
                                    )
                                    Spacer(modifier = Modifier.width(LARGE_PADDING))
                                    Text(
                                        modifier = Modifier.weight(2f),
                                        text = item.name.toString()
                                    )
                                }
                            }
                            item { 
                                Spacer(modifier = Modifier.height(MEDIUM_PADDING))
                            }

                        }
                    }
                }
            }
        }
    }
}