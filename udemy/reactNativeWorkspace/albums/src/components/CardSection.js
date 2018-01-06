import React from 'react';
import { View } from 'react-native';

const CardSection = (props) => {
    return (
        <View style={styles.containerStyle}>
            {props.children}
        </View>
    );
};

const styles = {
    containerStyle: {
        borderBottomWidth: 1,
        padding: 5,
        backgroundColor: '#fff',
        justifyContent: 'flex-start', // push items to start edge of container (left side)
        flexDirection: 'row', // horizontal alignment
        borderColor: '#ddd',
        position: 'relative',
    }
};

export default CardSection;