import React from 'react';
import { Text, View, Image, Linking } from 'react-native';
import Card from './Card';
import CardSection from './CardSection';
import Button from './Button';

// destructured album. before was ( props) then became ( {album} ) since we were doing props.album frequently
const AlbumDetail = ({ album }) => {
    const { title, artist, thumbnail_image, image, url} = album; // destructure album, props.album.title is what this does, for ex
    const { // destructure styles
        headerContentStyle, 
        thumbnailStyle, 
        thumbnailContainerStyle,
        headerTextStyle,
        imageStyle
    } = styles; 
    return (
        <Card>
            <CardSection>
                <View style={thumbnailContainerStyle} >
                    <Image style={thumbnailStyle} source={{ uri: thumbnail_image }} />
                </View>
                <View style={headerContentStyle}>
                    <Text style={headerTextStyle}>{title}</Text>
                    <Text>{artist}</Text>
                </View>
            </CardSection>

            <CardSection>
                <Image style={imageStyle} source={{ uri: image }}/>
            </CardSection>
            
            <CardSection>
                <Button onPress={() => Linking.openURL(url)}>
                    Buy '{title}' Now
                </Button>
            </CardSection>
        </Card>
    );
};

const styles = {
    headerContentStyle: {
        flexDirection: 'column',
        justifyContent: 'space-around',

    },
    headerTextStyle: {
        fontSize: 18
    }, 
    thumbnailStyle: {
        height: 50,
        width: 50
    },
    thumbnailContainerStyle: {
        justifyContent: 'center',
        alignItems: 'center',
        marginLeft: 10,
        marginRight: 10
    },
    imageStyle: {
        height: 300,
        flex: 1,
        width: null
    }
};

export default AlbumDetail;

