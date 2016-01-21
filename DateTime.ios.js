/**
 * Created by CnJon on 16/1/21.
 */
'use strict';

import React,{
    Component,
    StyleSheet,
    View,
    DatePickerIOS,
    TouchableOpacity,
    Navigator,
    Text,
    Dimensions
} from 'react-native';

const Screen = Dimensions.get('window');

export default class DateTimePicker extends Component {
    constructor(props) {
        super(props);
        this.state = {
            visible: false,
            mode: 'date',
            date: new Date()
        }
        this.callback = ()=>{};
    }

    showDatePicker(date, callback) {
        this.callback = callback;
        date = (date || new Date());

        this.setState({
            mode: 'date',
            visible: true,
            date: new Date(),
        });
    }

    showTimePicker(date, callback) {
        this.callback = callback;
        date = (date || new Date());

        this.setState({
            mode: 'time',
            visible: true,
            date: date,
        });
    }

    showDateTimePicker(date, callback) {
        this.callback = callback;
        date = (date || new Date());

        this.setState({
            mode: 'datetime',
            visible: true,
            date: date,
        });
    }

    onClose() {
        this.setState({
            visible: false,
        });
    }

    onComplete() {
        this.setState({
            visible: false,
        });
        this.callback(this.state.date);
    }

    onDateChange(date) {
        this.setState({date: date});
    }

    render() {
        return this.state.visible && (
                <View style={styles.container}>
                    <View style={styles.actionSheetContainer}>
                        <TouchableOpacity
                            style={styles.touchableOpacity}
                            activeOpacity={1}
                            onPress={()=>{this.onClose()}} />
                        <DatePickerIOS
                            date={this.state.date}
                            mode={this.state.mode}
                            onDateChange={(date)=>{this.onDateChange(date)}}
                            style = {styles.datePicker}
                        />
                        <View style={styles.separator}/>
                        <TouchableOpacity
                            onPress={()=>{this.onComplete()}}
                            style={styles.button}>
                            <Text>完成</Text>
                        </TouchableOpacity>
                        <TouchableOpacity
                            style={styles.touchableOpacity}
                            activeOpacity={1}
                            onPress={()=>{this.onClose()}} />
                    </View>
                </View>
            );
    }
}

const styles = StyleSheet.create({
    container:{
        top: 0,
        bottom: 0,
        left: 0,
        right: 0,
        backgroundColor: 'transparent',
        position: 'absolute'
    },
    actionSheetContainer: {
        height: Screen.height,
        justifyContent: 'center',
        backgroundColor: 'rgba(0, 0, 0, 0.5)'
    },
    datePicker: {
        backgroundColor: 'white',
    },
    touchableOpacity: {
        flex: 1,
    },
    button: {
        paddingVertical: 10,
        backgroundColor: 'white',
        justifyContent: 'center',
        alignItems: 'center'
    },
    separator: {
        height: 1,
        backgroundColor: '#CCC'
    }
});