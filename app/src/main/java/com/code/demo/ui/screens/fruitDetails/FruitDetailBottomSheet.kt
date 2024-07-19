package com.code.demo.ui.screens.fruitDetails

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.code.demo.R
import com.code.demo.model.EnumSwipDirection
import com.code.demo.model.FruitModel
import com.code.demo.ui.screens.fruitDetails.components.SwipeBoxComponent
import com.code.demo.ui.theme.greenColor
import com.code.demo.utils.convertDateStringFormat


//https://medium.com/@jpmtech/gestures-in-jetpack-compose-b838d49ddd25
@Composable
fun FruitDetailBottomSheet(
    fruitModel: FruitModel,
    onCloseClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    onArchiveClick: () -> Unit
) {

    var swipDirection by remember { mutableStateOf(EnumSwipDirection.SETLLE) }
    var titletext by remember { mutableStateOf("") }

    var startLeftAnimation by remember {
        mutableStateOf(false)
    }
    val leftAnimation = animateFloatAsState(
        targetValue = if (startLeftAnimation) 3f else 1f,
        animationSpec = tween(durationMillis = 3000), label = ""
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = titletext,
                style = TextStyle(
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            )
            IconButton(onClick = { onCloseClicked.invoke() }) {
                Icon(
                    Icons.Default.Clear,
                    contentDescription = "", tint = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))


        Text(
            text = fruitModel.detail,
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Black)
        )

        Spacer(modifier = Modifier.height(6.dp))

        val context = LocalContext.current
        SwipeBoxComponent(
            modifier = Modifier
                .minimumInteractiveComponentSize()
                .fillMaxWidth(),

            onDelete = {
                onDeleteClicked.invoke()
            },
            onEatFruit = {
                onArchiveClick.invoke()
            },
            onSwipeStartToEnd = {
                swipDirection = EnumSwipDirection.START_TO_END
                titletext = context.getString(R.string.produit_manger)
                startLeftAnimation =true

            },
            onSwipeEndToStart = {
                swipDirection = EnumSwipDirection.END_TO_START
                titletext = context.getString(R.string.produit_jeter)
            },
            onSwipeSettled = {
                swipDirection = EnumSwipDirection.SETLLE
                titletext = context.getString(R.string.produit_manger_ou_jetter)
               // startLeftAnimation =false
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Card(
                    modifier = Modifier
                        .wrapContentWidth()
                        .size(206.dp)
                        .align(Alignment.Center),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    )
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(fruitModel.imageResource),
                        contentDescription = fruitModel.name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                if (swipDirection != EnumSwipDirection.SETLLE) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(50.dp)
                            .background(
                                color = if (swipDirection == EnumSwipDirection.START_TO_END) greenColor else Color.Red,
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(28.dp)
                                .align(Alignment.Center),
                            imageVector = if (swipDirection == EnumSwipDirection.START_TO_END) ImageVector.vectorResource(
                                id = R.drawable.forkandknife
                            ) else Icons.Default.Delete,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }

                }

            }
        }


        Spacer(modifier = Modifier.width(12.dp))

        val dateAchat =
            stringResource(id = R.string.acheteLe) + " " + fruitModel.expirationDate.convertDateStringFormat() + stringResource(
                id = R.string.alaveille
            )


        Text(text = dateAchat, color = Color.Gray)

        Spacer(modifier = Modifier.height(8.dp))

        SwichQuantiteJetter()

        Spacer(modifier = Modifier.height(45.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            IconButton(
                modifier = Modifier
                    .background(color = Color.Red, shape = CircleShape)
                    .size(76.dp)
                    .alpha(leftAnimation.value)
                    .padding(14.dp), onClick = { }) {
                Icon(
                    modifier = Modifier.size(46.dp),
                    imageVector = Icons.Default.Delete, contentDescription = "", tint = Color.White
                )
            }

            IconButton(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                    .size(76.dp)
                    .padding(14.dp), onClick = { }) {
                Icon(
                    modifier = Modifier.size(46.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.forkandknife),
                    contentDescription = "",
                    tint = Color.White
                )
            }

        }

        Spacer(modifier = Modifier.height(22.dp))

    }
}

@Composable
fun SwichQuantiteJetter() {
    var isChecked by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier.fillMaxWidth(0.8f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(stringResource(id = R.string.indiqueQuantite) ,color = Color.Gray)
        Spacer(modifier = Modifier.width(12.dp))
        Switch(
            checked = isChecked,
            onCheckedChange = {
                isChecked = it
            }
        )
    }
}



