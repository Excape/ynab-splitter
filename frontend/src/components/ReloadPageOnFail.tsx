import React from 'react';
import {Button, Icon, Segment} from 'semantic-ui-react';

const ReloadPageOnFail = ({message}: { message: string }) => {

    function reloadPage() {
        if ("serviceWorker" in navigator) {
            navigator.serviceWorker.ready.then((registration) => {
                registration.unregister()
                window.location.reload()
            })
        }
    }

    return (
        <div>
                <Segment basic textAlign="center">
                    <em>{message}</em>
                </Segment>
                <Segment basic textAlign="center">
                        <Button onClick={() => reloadPage()}>
                            <Icon name="redo alternate" />
                            Reload
                        </Button>
                </Segment>
        </div>
    )
}

export default ReloadPageOnFail