/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React from 'react';
import {SafeAreaView, Text} from 'react-native';
import {RecoilRoot} from 'recoil';

function App(): JSX.Element {
  return (
    <RecoilRoot>
      <SafeAreaView>
        <Text>Oi</Text>
      </SafeAreaView>
    </RecoilRoot>
  );
}

export default App;
