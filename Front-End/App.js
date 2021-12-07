import { StatusBar } from 'expo-status-bar';
import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import Menus from './components/Menus';
import Stats from './components/Stats';
import Meal_Plan from './components/Meal_Plan';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { createBottomTabNavigator} from '@react-navigation/bottom-tabs';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';

const Tab = createBottomTabNavigator();
console.disableYellowBox = true;
console.disableRedBox = true;




export default function App() {
  return (

    <NavigationContainer>

      <Tab.Navigator screenOptions={{
        headerShown: false,
        tabBarActiveTintColor: '#B25D0F',
        tabBarInactiveTintColor: '#382E2E',
        tabBarStyle: styles.tabBar,
        tabBarLabelStyle: styles.tabBarLabel,
      }}>

        <Tab.Screen name="Meal Plan" component={Meal_Plan} options={{
          tabBarIcon: ({color}) => (
            <MaterialIcons name='menu-book' size={30} color={color}/>
          ),
          tabBarItemStyle: {left: 16},
          tabBarIconStyle: {top: 9},
        }}/>

        <Tab.Screen  name="Menus" component={Menus} options={{
          tabBarIcon: ({color}) => (
            <MaterialIcons name='fastfood' size={30} color={color}/>
          ),
          tabBarIconStyle: {top: 6},
        }}/>

        <Tab.Screen name="Stats" component={Stats} options={{
          tabBarIcon: ({color}) => (
            <MaterialIcons name='insert-chart' size={33} color={color}/>
          ),
          tabBarItemStyle: {right: 12},
          tabBarIconStyle: {top: 8},
        }}/>
      </Tab.Navigator>

    </NavigationContainer>
    
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'red',
    alignItems: 'center',
    justifyContent: 'center',
  },

  tabBar: {
    position: 'absolute',
    borderTopRightRadius: 40,
    borderTopLeftRadius: 40,
    height: 90,
    backgroundColor: '#EFD6C1',
  },

  tabBarLabel: {
    fontSize: 12,
  },
});
