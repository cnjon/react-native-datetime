/**
 * Created by CnJon on 16/1/21.
 */
'use strict';

import React,{Component, PropTypes} from 'react';
import {
    DatePickerIOS,
    Dimensions,
    Navigator,
    StyleSheet,
    Text,
    TouchableOpacity,
    View,
} from 'react-native';

const Screen = Dimensions.get('window');

export default class DateTimePicker extends Component {
    static propTypes = {
        okText: PropTypes.string
    };

    static defaultProps = {
        okText: "Ok"
    };

    constructor(props) {
        super(props);
        this.state = {
            visible: false,
            mode: 'date',
            date: new Date()
        };
        this.callback = ()=>{};
    }

    showDatePicker(date, callback) {
        this.callback = callback || this.props.onDateChange;
        date = (date || new Date());

        this.setState({
            mode: 'date',
            visible: true,
            date: date
        });
    }

    showTimePicker(date, callback) {
        this.callback = callback || this.props.onDateChange;
        date = (date || new Date());

        this.setState({
            mode: 'time',
            visible: true,
            date: date
        });
    }

    showDateTimePicker(date, callback) {
        this.callback = callback || this.props.onDateChange;
        date = (date || new Date());

        this.setState({
            mode: 'datetime',
            visible: true,
            date: date
        });
    }

    onClose() {
        this.setState({
            visible: false
        });
    }

    onComplete() {
        this.setState({
            visible: false
        });
        this.callback(this.props.date);
    }

    onDateChange(date) {
        this.setState({date: date});
    }

    render() {
        const styles = { ..._styles, ...this.props.styles}
        return this.state.visible && (
                <View style={styles.container}>
                    <View style={styles.actionSheetContainer}>
                        <TouchableOpacity
                            style={styles.touchableOpacity}
                            activeOpacity={1}
                            onPress={()=>this.onClose()} />
                        <TouchableOpacity
                            onPress={()=>this.onComplete()}
                            style={styles.button}>
                            <Text>{ this.props.okText }</Text>
                        </TouchableOpacity>
                        <View style={styles.separator}/>
                        <DatePickerIOS
                            // date={this.state.date}
                            // mode={this.state.mode}
                            // onDateChange={(date)=>this.onDateChange(date)}
                            style = {styles.datePicker}
                            {...this.props}
                        />
                    </View>
                </View>
            );
    }
}

const _styles = StyleSheet.create({
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
        alignItems: 'center',
    },
    touchableOpacity: {
        flex: 1,
    },
    button: {
        paddingHorizontal: 10,
        paddingVertical: 10,
        backgroundColor: 'white',
        justifyContent: 'flex-end',
        alignItems: 'flex-end'
    },
    separator: {
        height: 1,
        backgroundColor: '#CCC'
    }
});
