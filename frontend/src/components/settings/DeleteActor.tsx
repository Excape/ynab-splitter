import {Button, Icon, Loader} from 'semantic-ui-react';
import React from 'react';
import Cookies from 'js-cookie';

type Props = {
    actorName: string,
    onDeleteActor: () => Promise<void>
}

const DeleteActor = ({actorName, onDeleteActor}: Props) => {

    const [isLoading, setIsLoading] = React.useState(false)

    function deleteActor(actorName: string) {
        setIsLoading(true)
        const request = {
            actorName: actorName
        }

        fetch("/api/v1/user/deleteActor", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                'X-XSRF-TOKEN': Cookies.get("XSRF-TOKEN") ?? 'NULL'
            },
            body: JSON.stringify(request)
        })
            .then(_ => {
                return onDeleteActor().then(() => setIsLoading(false))
            })
    }

    if (isLoading) {
        return (<Loader active inline='centered'/>)
    }

    return (
        <Button icon onClick={() => deleteActor(actorName)}>
            <Icon name="remove" color="red"/>
        </Button>
    )
}

export default DeleteActor