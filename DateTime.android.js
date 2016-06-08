/**
 * Created by CnJon on 16/1/21.
 */
'use strict';

import React,{Component, PropTypes} from 'react';
import {
    NativeModules,
    View,
} from 'react-native';

const RCTDateTimePicker = NativeModules.DateTimePicker;

export default class DateTimePicker extends Component {
    static propTypes = {
        cancelText: PropTypes.string,
        okText:  PropTypes.string
    };

    static defaultProps = {
        cancelText: 'Cancel',
        okText: 'Ok'
    };

    constructor(props) {
        super(props);
    }

    showDatePicker(date, callback) {
        date = date || new Date();
        callback = callback || this.props.onDateChange;
        var options = {
            ...this.props,
            year:date.getFullYear(),
            month:date.getMonth(),
            day:date.getDate()
        };
        RCTDateTimePicker.showDatePicker(options, function (year, month, day) {
            date.setFullYear(year);
            date.setMonth(month);
            date.setDate(day);
            callback(date);
        });
    }

    showTimePicker(date, callback) {
        date = date || new Date();
        callback = callback || this.props.onDateChange;
        var options = {
            ...this.props,
            hour:date.getHours(),
            minute:date.getMinutes()
        };
        RCTDateTimePicker.showTimePicker(options, function (hour, minute) {
            date.setHours(hour);
            date.setMinutes(minute);
            callback(date);
        });
    }

    showDateTimePicker(date, callback) {
        date = date || new Date();
        callback = callback || this.props.onDateChange;
        var options = {
            ...this.props,
            year:date.getFullYear(),
            month:date.getMonth(),
            day:date.getDate(),
            hour:date.getHours(),
            minute:date.getMinutes()
        };
        RCTDateTimePicker.showDateTimePicker(options, function (year, month, day, hour, minute) {
            date.setFullYear(year);
            date.setMonth(month);
            date.setDate(day);
            date.setHours(hour);
            date.setMinutes(minute);
            callback(date);
        });
    }

    render() {
        return null;
    }
}
