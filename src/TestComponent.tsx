// src/TestComponent.tsx
import React, { useState } from 'react';
import { View, Button, Text, StyleSheet } from 'react-native';
import { NativeModules } from 'react-native';

const { MyNativeModule } = NativeModules;

interface User {
    id: string;
    email: string;
    name: string;
    phone: string;
    created_at: string;
}

interface LoginResponse {
    token: string;
    user: User;
}

const TestComponent: React.FC = () => {
    const [result, setResult] = useState<LoginResponse | null>(null);
    const [error, setError] = useState<string | null>(null);
    console.log("DGDSDS")
    const handleLogin = () => {
        MyNativeModule.login('usuario@ejemplo.com', 'password123', (err: string, res: LoginResponse) => {
            if (err) {
                console.log("ERROCILLO")
                setError(err);
                setResult(null);
            } else {
                console.log(res)
                setResult(res);
                setError(null);
            }
        });
    };

    return (
        <View style={styles.container}>
            <Button title="Test Login" onPress={handleLogin} />
            {result && (
                <View style={styles.resultContainer}>
                    <Text>Token: {result.token}</Text>
                    <Text>User: {JSON.stringify(result.user)}</Text>
                </View>
            )
            }

            {error && <Text style={styles.errorText}>Error: {error}</Text>}

        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
    },
    resultContainer: {
        marginTop: 20,
    },
    errorText: {
        color: 'red',
        marginTop: 20,
    },
});

export default TestComponent;