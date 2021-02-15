import * as Cookies from "js-cookie";
import {UserSession} from './types';
import React from 'react';

export type Session = {
    session: UserSession | undefined,
    login: () => void
    logout: () => void
}

export const sessionCookieName = "reactSession"

export const SessionContext = React.createContext(
    {
        session: undefined,
        login: () => {
        },
        logout: () => {
        }
    } as Session)

export const setSessionCookie = (session: UserSession): void => {
    clearSessionCookie()
    Cookies.set(sessionCookieName, session, {sameSite: 'strict'});
};

export const clearSessionCookie = (): void => {
    Cookies.remove(sessionCookieName);
};

export const getSessionCookie = (): UserSession | undefined => {
    const sessionCookie = Cookies.get(sessionCookieName);

    if (sessionCookie === undefined) {
        return undefined;
    } else {
        return JSON.parse(sessionCookie) as UserSession;
    }
};


