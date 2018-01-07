import React from 'react';
import { View, ActivityIndicator } from 'react-native';

const Spinner = (props) => {
    // if props.size is null, then do large by default
    return (
        <View style={styles.spinnerStyle}>
            <ActivityIndicator size={props.size || 'large'} />
        </View>
    );
};

const styles = {
    spinnerStyle: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center'
    }
};

export { Spinner };