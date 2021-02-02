import {vapid_public_key} from '../env';
import Cookies from 'js-cookie';
import {UserSession} from '../types';

const serviceWorkerAvailable = () => 'serviceWorker' in navigator;

export const getExistingSubscription = (): Promise<PushSubscription> => {

    if (serviceWorkerAvailable()) {
        return navigator.serviceWorker.ready
            .then(reg => {
                return reg.pushManager.getSubscription();
            }).then(sub => sub === null ? Promise.reject() : sub)
    } else {
        return Promise.reject('service Worker not available')
    }
}

export const subscribe = (): Promise<PushSubscription> => {
    if (serviceWorkerAvailable()) {
        return navigator.serviceWorker.ready.then(reg =>
            reg.pushManager.subscribe({
                    userVisibleOnly: true,
                    applicationServerKey: vapid_public_key
                }
            )
        )
    } else {
        return Promise.reject("Service worker not available")
    }
}

export const unsubscribe = (): Promise<boolean> => {
    return navigator.serviceWorker.ready.then(reg => {
        return reg.pushManager.getSubscription().then(sub => {
            return sub!.unsubscribe()
        })
    })
}

export const sendSubscription = (sub: PushSubscription, session: UserSession | undefined) => {
    const request = {
        actorName: session?.actor,
        subscription: sub.toJSON()
    }
    return fetch("/api/v1/user/subscribePush", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
            'X-XSRF-TOKEN': Cookies.get("XSRF-TOKEN") ?? 'NULL'
        },
        body: JSON.stringify(request)
    })
}
